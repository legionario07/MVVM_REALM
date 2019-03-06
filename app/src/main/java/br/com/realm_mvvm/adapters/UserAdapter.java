package br.com.realm_mvvm.adapters;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.realm_mvvm.R;
import br.com.realm_mvvm.databinding.ItemUserBinding;
import br.com.realm_mvvm.models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> users;

    public UserAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(

                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_user,
                viewGroup,
                false
        );

        return new UserViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        User user = users.get(i);
        userViewHolder.itemUserBinding.setUser(user);

        if(i % 2 == 0){
            userViewHolder.itemUserBinding.ll.setBackgroundColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getItem(int position){
        return this.users.get(position);
    }

    public static  class UserViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private ItemUserBinding itemUserBinding;

        public UserViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            this.itemUserBinding = itemView;
            itemUserBinding.ll.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 1, 1, "Update");
            menu.add(this.getAdapterPosition(), 2, 2, "Delete");
        }
    }

}
