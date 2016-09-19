package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.viewmodel.ContentListViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 29/8/16.
 */

public interface ContentListDataDownloadCompleted {
     void receivedContentListViewModelData(ArrayList<ContentListViewmodel> data);
}
