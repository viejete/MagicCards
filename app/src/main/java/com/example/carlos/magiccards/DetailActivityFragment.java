package com.example.carlos.magiccards;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.alexvasilkov.events.Events;

import com.example.carlos.magiccards.databinding.FragmentDetailBinding;


public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        Events.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_detail , container , false);
        View view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if (i != null) {
            Card card = (Card) i.getSerializableExtra("card");

            if (card != null) {
                updateUi(card);
            }
        }

        return view;
    }

    @Events.Subscribe("card-selected")
    private void onCardSelected (Card card) {
        updateUi(card);
    }

    private void updateUi (Card card) {

        Glide.with(getContext()).load(card.getImageUrl()).into(binding.ivCard);
        binding.cardName.setText(card.getName());
        binding.cardType.setText(card.getType());
        binding.cardRarity.setText(card.getRarity());
        binding.cardText.setText(card.getText());

    }
}
