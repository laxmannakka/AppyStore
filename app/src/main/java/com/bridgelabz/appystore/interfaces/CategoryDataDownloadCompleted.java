package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 24/8/16.
 */

public interface CategoryDataDownloadCompleted {
    void receivedCategoryViewModelData(ArrayList<CategoryViewmodel> data);
}
