package com.company.oils.ui.fragments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.company.oils.databinding.FragmentHistoryBinding;
import com.company.oils.ui.adapters.HistoryAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        firebaseFirestore = FirebaseFirestore.getInstance();

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(historyViewModel.getName() != null){
            binding.placeholder.setVisibility(View.INVISIBLE);
            binding.menu.setVisibility(View.VISIBLE);

            binding.name.setText(historyViewModel.getName());

            historyViewModel.GetData(historyViewModel.getName(), (e) -> {
                if(e.size() == 0){
                    binding.placeholder.setVisibility(View.VISIBLE);
                    return;
                }
                RecyclerView recyclerView = binding.itemsHistory;
                recyclerView.setAdapter(new HistoryAdapter(e, getContext(), false));
            });

        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}