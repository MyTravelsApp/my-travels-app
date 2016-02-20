package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    private OnClickAddButtonListener onClickAddButtonListener;

    public CategoryAdapter(final Context context, final List<CategoryModel> pList) {
        super(context,pList);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+1;
    }

    @Override
    public void onBindViewHolder(AbtractCategoryViewHolder holder, int position) {
        if(position == getList().size()){
          final AddCategoryViewHolder addCategoryViewHolder = (AddCategoryViewHolder) holder;
            addCategoryViewHolder.lv_row_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addCategoryViewHolder.lv_row_button.setVisibility(Button.GONE);
                    addCategoryViewHolder.text_category.setVisibility(Button.VISIBLE);
                    addCategoryViewHolder.category_linear_buttons.setVisibility(LinearLayout.VISIBLE);
                }
            });
            addCategoryViewHolder.lv_row_button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickAddButtonListener != null){
                        onClickAddButtonListener.save(new CategoryModel(CategoryModel.DEFAULT_ID, addCategoryViewHolder.text_category.getText().toString(), false));
                        resetListCategory(addCategoryViewHolder);
                    }
                }
            });
            addCategoryViewHolder.lv_row_button_cancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    resetListCategory(addCategoryViewHolder);
                }
            });
        } else {
            ListCategoryViewHolder listCategoryViewHolder = (ListCategoryViewHolder) holder;
            final CategoryModel model = getList().get(position);
            listCategoryViewHolder.lv_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getOnItemClickListener() != null) {
                        getOnItemClickListener().onItemClicked(model);
                    }
                }
            });
            listCategoryViewHolder.txtTitle.setText(model.getName());
        }

    }

    private void resetListCategory(AddCategoryViewHolder addCategoryViewHolder){
        addCategoryViewHolder.category_linear_buttons.setVisibility(LinearLayout.GONE);
        addCategoryViewHolder.text_category.setVisibility(Button.GONE);
        addCategoryViewHolder.text_category.setText("");
        addCategoryViewHolder.lv_row_button.setVisibility(Button.VISIBLE);
    }

    @Override
    public AbtractCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_CELL){
            final View view = getLayoutInflater().inflate(R.layout.row_category, parent, false);
            final ListCategoryViewHolder holder = new ListCategoryViewHolder(view);
            return holder;
        } else {
            final View view = getLayoutInflater().inflate(R.layout.row_category_button_add, parent, false);
            final AddCategoryViewHolder holderButton = new AddCategoryViewHolder(view);
            return  holderButton;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return (position == getList().size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    static abstract class AbtractCategoryViewHolder extends  RecyclerView.ViewHolder {
        public AbtractCategoryViewHolder(final View itemView) {
            super(itemView);
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
    }

    static  class AddCategoryViewHolder extends  AbtractCategoryViewHolder{
        @Bind(R.id.lv_row_categories_button)
        Button lv_row_button;

        @Bind(R.id.txt_new_category)
        EditText text_category;

        @Bind(R.id.category_linear_buttons)
        LinearLayout category_linear_buttons;

        @Bind(R.id.category_button_add)
        Button lv_row_button_add;

        @Bind(R.id.category_button_cancel)
        Button lv_row_button_cancel;

        public AddCategoryViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnClickAddButtonListener {
        void save(CategoryModel model);
    }


    public void setOnClickAddButtonListener(OnClickAddButtonListener onClickAddButtonListener) {
        this.onClickAddButtonListener = onClickAddButtonListener;
    }

}
