package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.model.CategoryModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author stefani
 */
public class CategoryAdapter extends AbstractAdapter<CategoryModel, CategoryAdapter.AbtractCategoryViewHolder> {

    private static int VIEW_TYPE_FOOTER = 0;
    private static int VIEW_TYPE_CELL = 1;

    private int positionSelected = -1;
    private boolean visibleAddCategory;

    private OnClickAddButtonListener onClickAddButtonListener;

    public CategoryAdapter(final Context context, final List<CategoryModel> pList) {
        super(context, pList);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public void onBindViewHolder(final AbtractCategoryViewHolder holder, final int position) {
        //Viewholer for add one new category
        if (position == getList().size()) {
            final AddCategoryViewHolder addCategoryViewHolder = (AddCategoryViewHolder) holder;
            addCategoryViewHolder.reset();
            addCategoryViewHolder.lv_row_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    visibleAddCategory = true;
                    if (positionSelected != -1) {
                        notifyItemChanged(positionSelected);
                    }
                    addCategoryViewHolder.enabledTextCategory();
                }
            });
            addCategoryViewHolder.category_button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickAddButtonListener != null) {
                        onClickAddButtonListener.save(new CategoryModel(CategoryModel.DEFAULT_ID, addCategoryViewHolder.text_category.getText().toString(), false), -1);
                        addCategoryViewHolder.reset();
                    }
                }
            });
            addCategoryViewHolder.category_button_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCategoryViewHolder.reset();
                }
            });
            //Viewholder for see the list of category
        } else {
            final ListCategoryViewHolder listCategoryViewHolder = (ListCategoryViewHolder) holder;
            listCategoryViewHolder.reset();
            final CategoryModel model = getList().get(position);
            listCategoryViewHolder.lv_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(visibleAddCategory){
                        notifyItemChanged(getList().size());
                        visibleAddCategory = false;
                    }
                    if (positionSelected != -1) {
                        notifyItemChanged(positionSelected);
                    }
                    positionSelected = position;
                    listCategoryViewHolder.enabledEdit();
                }
            });
            listCategoryViewHolder.txtTitle.setText(model.getName());
            listCategoryViewHolder.category_button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickAddButtonListener != null) {
                        model.setName(listCategoryViewHolder.text_category.getText().toString());
                        onClickAddButtonListener.save(model, position);
                        listCategoryViewHolder.reset();
                    }
                }
            });

            listCategoryViewHolder.category_button_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listCategoryViewHolder.reset();
                }
            });
        }
    }


    @Override
    public AbtractCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CELL) {
            final View view = getLayoutInflater().inflate(R.layout.row_category, parent, false);
            final ListCategoryViewHolder holder = new ListCategoryViewHolder(view);
            return holder;
        } else {
            final View view = getLayoutInflater().inflate(R.layout.row_category_button_add, parent, false);
            final AddCategoryViewHolder holderButton = new AddCategoryViewHolder(view);
            return holderButton;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return (position == getList().size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    static abstract class AbtractCategoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.category_button_cancel)
        ImageButton category_button_cancel;

        @Bind(R.id.category_linear_buttons)
        LinearLayout category_linear_buttons;

        @Bind(R.id.category_button_add)
        ImageButton category_button_add;

        @Bind(R.id.txt_edit_category)
        EditText text_category;

        public AbtractCategoryViewHolder(final View itemView) {
            super(itemView);
        }

        private void reset(){
            this.category_linear_buttons.setVisibility(View.GONE);
        }
        private void enabled(String textCategory){
            this.category_linear_buttons.setVisibility(View.VISIBLE);
            this.text_category.setText(textCategory);
            this.text_category.setVisibility(View.VISIBLE);
        }
    }

    static class ListCategoryViewHolder extends AbtractCategoryViewHolder {

        @Bind(R.id.lv_row_categories)
        RelativeLayout lv_row;

        @Bind(R.id.txt_title)
        TextView txtTitle;

        public ListCategoryViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        private void reset(){
            super.reset();
            this.txtTitle.setVisibility(View.VISIBLE);
        }
        private void enabledEdit(){
            super.enabled(txtTitle.getText().toString());
            this.txtTitle.setVisibility(View.GONE);
        }
    }

    static class AddCategoryViewHolder extends AbtractCategoryViewHolder {
        @Bind(R.id.lv_row_categories_button)
        Button lv_row_button;

        public AddCategoryViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void reset(){
            super.reset();
            this.lv_row_button.setVisibility(View.VISIBLE);
        }
        private void enabledTextCategory(){
            super.enabled("");
            this.lv_row_button.setVisibility(View.GONE);
        }
    }

    public interface OnClickAddButtonListener {
        void save(CategoryModel model, int position);
    }


    public void setOnClickAddButtonListener(OnClickAddButtonListener onClickAddButtonListener) {
        this.onClickAddButtonListener = onClickAddButtonListener;
    }
}
