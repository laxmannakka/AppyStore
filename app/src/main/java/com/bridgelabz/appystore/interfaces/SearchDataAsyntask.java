package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.model.SearchDatamodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 16/9/16.
 */

public interface SearchDataAsyntask {
    void loadedSearchDataFromServer(ArrayList<SearchDatamodel> searchmodel);

}
