package com.example.e_library;
import android.widget.Filter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FilterCategory extends Filter {
    //arraylist for the search
    ArrayList<ModelCategory> filterList;

    //adapter in which filter needs to e implemented
    AdapterCategory adapterCategory;

    //constructor
    public FilterCategory(ArrayList<ModelCategory> filterList, AdapterCategory adapterCategory) {
        this.filterList = filterList;
        this.adapterCategory = adapterCategory;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
      FilterResults results = new FilterResults();

      //value shouldn't be null or empty
     if (constraint != null && constraint.length()> 0){

         //change to upper case or lower case to avoid case sensitivity
         constraint = constraint.toString().toUpperCase();
         ArrayList<ModelCategory> filteredModels = new ArrayList<>();

         for (int i=0; i< filterList.size(); i++){

             //validate
             if (filterList.get(i).getCategory().toUpperCase().contains(constraint)){

                 //add to filteredModels
                 filteredModels.add(filterList.get(i));
             }

         }

         results.count = filteredModels.size();
         results.values = filteredModels;

     }
     else{
         results.count = filterList.size();
         results.values = filterList;
     }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply filter changes
        adapterCategory.categoryArrayList = (ArrayList<ModelCategory>)results.values;

        //notify changes
        adapterCategory.notifyDataSetChanged();

    }
}
