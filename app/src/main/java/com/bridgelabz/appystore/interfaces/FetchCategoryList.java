package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 24/8/16.
 */

public interface FetchCategoryList {
     void receivedCategoryViewData(ArrayList<CategoryViewmodel> viewmodelArrayList);
}
