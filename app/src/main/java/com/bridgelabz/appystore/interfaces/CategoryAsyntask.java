package com.bridgelabz.appystore.interfaces;

import com.bridgelabz.appystore.model.Categorymodel;
import com.bridgelabz.appystore.viewmodel.CategoryViewmodel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 24/8/16.
 */

public interface CategoryAsyntask {
       void loadedCategoryDataFromServer(ArrayList<Categorymodel> model);
}
