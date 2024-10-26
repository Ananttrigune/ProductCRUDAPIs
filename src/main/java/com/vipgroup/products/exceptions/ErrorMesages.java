package com.vipgroup.products.exceptions;

public interface ErrorMesages {
    String INVALID_PAGINATIONCOUNTERS = "Input valid parameters for pageSize and/or pageNumber.\nValid values as pageSize>0 and pageNumber>=0";
    String INVALID_SORTING_FIELDNAME = " - Field name invalid. \nField names should be Name, Description, Category.";
    String INVALID_SORTING_ORDERTYPE = " - Sorting order type invalid. \nSorting order types should be ASC or DESC.";

}