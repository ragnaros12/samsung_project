package com.company.oils.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.company.oils.core.models.Oil;
import com.company.oils.core.models.OilContainer;
import com.company.oils.databinding.FragmentHomeBinding;
import com.company.oils.ui.adapters.PriceAdapter;

import java.util.List;
import java.util.function.Consumer;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView priceList = binding.pricesList;

        homeViewModel.get(new Consumer<OilContainer>() {
            @Override
            public void accept(OilContainer oils) {
                priceList.setAdapter(new PriceAdapter(oils.getOils(), oils.getNames(), getActivity().getApplicationContext()));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}