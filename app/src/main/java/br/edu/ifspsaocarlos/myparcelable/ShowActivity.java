package br.edu.ifspsaocarlos.myparcelable;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import br.edu.ifspsaocarlos.myparcelable.model.Empresa;
import br.edu.ifspsaocarlos.myparcelable.model.Funcionarios;

public class ShowActivity extends AppCompatActivity {

    TextView tvName;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvName = (TextView)findViewById(R.id.tvNome);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

        // Obtendo dados de objeto Parcelable passado da MainActivity
        /*
            Você utiliza do método Parcels.unwrap para recuperar o Parceable;
         */
        Empresa empresa = getIntent().getParcelableExtra(MainActivity.INTENT_EMPRESA);

        tvName.setText(empresa.getNomeEmpresa());

        for(Funcionarios f: empresa.getFuncionarios()) {
            TextView tv = new TextView(this);
            tv.setText(f.getNome());
            tv.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(tv);
        }


    }
}
