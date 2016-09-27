package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.viewmodel.SearchViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 16/9/16.
 */

public interface SearchDataDownLoadCompleted {
     void receivedSerachViewModelData(ArrayList<SearchViewmodel> model);
}
