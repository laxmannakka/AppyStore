package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.model.ContentListmodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;
import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 29/8/16.
 */

public interface ContentListAsynTask {
     void loadedContentListDataFromServer(ArrayList<ContentListmodel> data);
}
