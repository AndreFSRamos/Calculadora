package br.edu.opet.minhacalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
/*
    -- Atividade da faculdade. --

    Calculadora funcional usando java.
    Criado efeito para os botões do tipo numério (pasta "drawable", arquivos @shape_number_normal,
    @shape_number_selection, @shape_number effect).*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Declaração de variaveis.
    private Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,soma,divi,sub,multi,ponto,limpar,igual;

    private TextView TxtExp,TxtResulado;

    private ImageView backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentStart();// Função para iniciar as vairiaveis declaradas.
        getSupportActionBar().hide();//Comando para ocultar a barra de status.

        //Atribuição de habilidade na MainActivity para ouvir os clicks.
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        sub.setOnClickListener(this);
        multi.setOnClickListener(this);
        divi.setOnClickListener(this);

        limpar.setOnClickListener(new View.OnClickListener() {//Metodo do botão limpar.
            @Override
            public void onClick(View view) {
                TxtExp.setText("");
                TxtResulado.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {//Metodo do botão backscpace
            @Override
            public void onClick(View view) {
                //obtem o valor através do "txt_exp" e converte em uma string.
                TextView expressao = findViewById(R.id.txt_exp);
                String string  = expressao.getText().toString();

                if(!string.isEmpty()){/*verifica se a variavel está vazia, caso SIM "NEGANDO",
                  é consultado o tamanho da string com o "length" e excluido o ultimo valor da string*/
                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtExpressao = string.substring(var0,var1);
                    expressao.setText(txtExpressao);
                }
                TxtResulado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() { //Função resposavel pelos calculos.
            @Override
            public void onClick(View view) {
                try {
                    Expression expression = new ExpressionBuilder(TxtExp.getText().toString()).build();
                    double resultado = expression.evaluate();
                    long longresultado = (long) resultado;

                    if (resultado == (double) longresultado){
                        TxtExp.setText((CharSequence) String.valueOf(longresultado));
                    }else {
                        TxtExp.setText((CharSequence) String.valueOf(resultado));
                    }
                }catch (Exception e){

                }
            }
        });
    }

    private void ComponentStart(){// Função para relacionar as variaveis com seus respectivos ID.
        num1 = findViewById(R.id.bt_um);
        num2 = findViewById(R.id.bt_dois);
        num3 = findViewById(R.id.bt_tres);
        num4 = findViewById(R.id.bt_quatro);
        num5 = findViewById(R.id.bt_cinco);
        num6 = findViewById(R.id.bt_seis);
        num7 = findViewById(R.id.bt_sete);
        num8 = findViewById(R.id.bt_oito);
        num9 = findViewById(R.id.bt_nove);
        num0 = findViewById(R.id.bt_zero);
        soma = findViewById(R.id.bt_soma);
        sub = findViewById(R.id.bt_sub);
        divi = findViewById(R.id.bt_divicao);
        multi = findViewById(R.id.bt_mult);
        ponto = findViewById(R.id.bt_ponto);
        igual = findViewById(R.id.bt_igual);
        limpar = findViewById(R.id.bt_limpar);
        TxtExp = findViewById(R.id.txt_exp);
        TxtResulado = findViewById(R.id.txt_resultado);
        backspace = findViewById(R.id.bt_backspace);
    }

    public void ADDExp(String string, boolean limpar_dados){//Função para ADD caracteres na expressão.

        if(TxtResulado.getText().equals("")){
            TxtExp.setText(" ");
        }

        if(limpar_dados){
            TxtResulado.setText(" ");
            TxtExp.append(string);
        }else{
            TxtExp.append(TxtResulado.getText());
            TxtExp.append(string);
            TxtResulado.setText(" ");
        }
    }

    @Override
    public void onClick(View v) {//Função para seleção dos botões.
        switch (v.getId()){
            case R.id.bt_zero:
                ADDExp("0", true);
                break;
            case R.id.bt_um:
                ADDExp("1", true);
                break;
            case R.id.bt_dois:
                ADDExp("2", true);
                break;
            case R.id.bt_tres:
                ADDExp("3", true);
                break;
            case R.id.bt_quatro:
                ADDExp("4", true);
                break;
            case R.id.bt_cinco:
                ADDExp("5", true);
                break;
            case R.id.bt_seis:
                ADDExp("6", true);
                break;
            case R.id.bt_sete:
                ADDExp("7", true);
                break;
            case R.id.bt_oito:
                ADDExp("8", true);
                break;
            case R.id.bt_nove:
                ADDExp("9", true);
                break;
            case R.id.bt_soma:
                ADDExp("+", false);
                break;
            case R.id.bt_sub:
                ADDExp("-", false);
                break;
            case R.id.bt_mult:
                ADDExp("*", false);
                break;
            case R.id.bt_divicao:
                ADDExp("/", false);
                break;
            case R.id.bt_ponto:
                ADDExp(".", true);
                break;
        }
    }
}