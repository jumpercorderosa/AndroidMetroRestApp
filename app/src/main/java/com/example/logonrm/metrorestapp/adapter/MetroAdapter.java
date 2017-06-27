package com.example.logonrm.metrorestapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logonrm.metrorestapp.R;
import com.example.logonrm.metrorestapp.model.Linha;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MetroAdapter extends RecyclerView.Adapter<MetroAdapter.AndroidViewHolder> {

    private List<Linha> linhas;
   // private Context context;
    private OnItemClickListener listener;

    public MetroAdapter(List<Linha> linhas, OnItemClickListener onItemClickListener) {
        this.linhas = linhas;
    }

    //Cria o stack que ele precisa para funcionar
    @Override
    public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View meuLayout = inflater.inflate(R.layout.linhas,
                parent, false);

        return new AndroidViewHolder(meuLayout);

    }

    public class AndroidViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImg;
        public TextView tvLinha;
        public TextView tvNumero;

        //tem que associar o elemento com o valor
        //recebe uma view pq tem um layout a ser utilizado nesse cara
        public AndroidViewHolder(View itemView) {

            //super chama o construtor da classe pai (RecyclerView)
            super(itemView);

            //bind das informações do xml com os componentes
            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvLinha = (TextView) itemView.findViewById(R.id.tvLinha);
            tvNumero = (TextView)itemView.findViewById(R.id.tvNumero);

        }
    }


    @Override
    public void onBindViewHolder(AndroidViewHolder holder, final int position) {

        //estou pegando o holder.layout.o set on clickListenter
        holder.tvLinha.setText(linhas.get(position).getCor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                listener.onItemClick(linhas.get(position));
            }

        });

        holder.tvLinha.setText(linhas.get(position).getNumero());

        Picasso.with(holder.itemView.getContext())
                .load(linhas.get(position).getUlrImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher) //pode colocar uma imagem de erro
                .into(holder.ivImg);
    }


    @Override
    public int getItemCount() {
        return linhas.size();
    }

    public void update(List<Linha> linhas) {
        this.linhas = linhas;
        //notifico que foram setados novos valores nessa lista
        notifyDataSetChanged();
    }
}
