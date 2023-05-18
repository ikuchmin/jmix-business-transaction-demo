package com.company.businesstransactiondemo.screen.t004;

import io.jmix.ui.screen.*;
import com.company.businesstransactiondemo.entity.T004;

@UiController("T004.browse")
@UiDescriptor("t004-browse.xml")
@LookupComponent("t004sTable")
public class T004Browse extends StandardLookup<T004> {
}