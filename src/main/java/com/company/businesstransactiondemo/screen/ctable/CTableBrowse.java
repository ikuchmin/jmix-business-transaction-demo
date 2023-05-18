package com.company.businesstransactiondemo.screen.ctable;

import io.jmix.ui.screen.*;
import com.company.businesstransactiondemo.entity.CTable;

@UiController("CTable.browse")
@UiDescriptor("c-table-browse.xml")
@LookupComponent("cTablesTable")
public class CTableBrowse extends StandardLookup<CTable> {
}