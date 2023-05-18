package com.company.businesstransactiondemo.screen.ctable;

import com.company.businesstransactiondemo.entity.T004;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.businesstransactiondemo.entity.CTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@UiController("CTable.edit")
@UiDescriptor("c-table-edit.xml")
@EditedEntityContainer("cTableDc")
public class CTableEdit extends StandardEditor<CTable> {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private CollectionLoader<T004> t004sDl;

    @Subscribe("modifyT004DcBtn")
    public void onModifyT004DcBtnClick(Button.ClickEvent event) {
        var newT004 = dataContext.create(T004.class);
        newT004.setKtopl(UUID.randomUUID().toString());
    }

    @Subscribe("t004Field.entityLookup")
    public void onT004FieldEntityLookup(Action.ActionPerformedEvent event) {
        screenBuilders.lookup(T004.class, this).show();
    }

    @Transactional
    @Install(to = "t004sDl", target = Target.DATA_LOADER)
    public List<T004> t004sDlLoadDelegate(LoadContext<T004> loadContext) {

        return transactionTemplate.execute(tStatus -> {
            tStatus.setRollbackOnly();

            var em = EntityManagerFactoryUtils
                    .getTransactionalEntityManager(entityManagerFactory);

            var modified = dataContext.getModified()
                    .stream().filter(e -> e instanceof T004)
                    .collect(Collectors.toList());

            modified.forEach(em::persist);


            em.flush();

            return dataManager.load(T004.class)
                    .query("select e from T004 e").list();
        });
    }

    @Subscribe("t004sTable.refresh")
    public void onT004sTableRefresh(Action.ActionPerformedEvent event) {
        t004sDl.load();
    }
}