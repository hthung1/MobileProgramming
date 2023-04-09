package com.example.freshfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.freshfood.adapter.SearchAdapter;
import com.example.freshfood.api.ApiService;
import com.example.freshfood.model.SearchFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {
    private RecyclerView search_rcv;
    private SearchAdapter searchAdapter;
    private List<SearchFood> searchFoodList;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSearch("");
    }
    private void getSearch(String value){
        ApiService.apiService.searchFood(value).enqueue(new Callback<List<SearchFood>>() {
            @Override
            public void onResponse(Call<List<SearchFood>> call, Response<List<SearchFood>> response) {
                if(response.isSuccessful()){
                    progressBar = findViewById(R.id.progress);
                    progressBar.setVisibility(View.GONE);
                    searchFoodList = response.body();
                    search_item(searchFoodList);
                }
            }

            @Override
            public void onFailure(Call<List<SearchFood>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void search_item(List<SearchFood> list){
        search_rcv = findViewById(R.id.search_rcv);
        search_rcv.setLayoutManager(new LinearLayoutManager(Search.this,RecyclerView.VERTICAL,false));
        search_rcv.setAdapter(new SearchAdapter(list,Search.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_item).getActionView();
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSearch(newText);
                return false;
            }
        });
        return true;
    }
}