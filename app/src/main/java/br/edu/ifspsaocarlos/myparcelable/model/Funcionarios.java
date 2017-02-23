package br.edu.ifspsaocarlos.myparcelable.model;


import android.os.Parcel;
import android.os.Parcelable;

import br.edu.ifspsaocarlos.myparcelable.ExampleParcelable;

/**
 * Created by lucas on 15/02/17.
 */
/*
A classe funcionário também deve ser um @Parcel
por quê é serializada junto da classe Empresa.
 */

public class Funcionarios implements Parcelable {
    private String nome;

    public Funcionarios(String nome) {
        this.nome = nome;
    }

    public Funcionarios() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
	Parcelable fornece um protocolo extremamente eficiente para escrever e ler objetos
	a partir de Parcels.
	*/

    /*
	construtor >> Observe que Parcelable chama o construtor da classe que
	recebe um Parcel permitindo ler dados e passar os atributos.
	*/
    protected Funcionarios(Parcel in) {
        nome = in.readString();
    }

    /*
	int describeContents() >> Um inteiro que descreve os tipos de objetos especiais contidos nesta
	instância Parcelable.
	Se o objeto incluir um descritor de arquivo na saída no WriteToParcel (Parcel, int).
	O valor de retorno desse método deve incluir o bit descritor de arquivo.
	*/
    @Override
    public int describeContents() {
        return 0;
    }

    /*
    writeToParcel >> Escreve um objeto Parcelable
  writeToParcel >> Responsável por serializar as informações da classe.
  Parcel dest >> O Parcel em que o objeto deve ser escrito.
  int flags >> Sinalizadores adicionais sobre como o objeto deve ser escrito.
               Pode ser 0 ou a flag pode ser um valor de retorna de uma função.
  */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
    }

    /*
    Classe aninhada >> Interface que deve ser implementada e abastecida com um
    campo público CREATOR que gera instâncias da classe Parcelable a partir de Parcel.
    */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Funcionarios> CREATOR = new Parcelable.Creator<Funcionarios>() {
        /*
		Cria uma nova instância da classe Parcelable, instanciando-a de Parcel,
		cujos dados foram previamente escritos por Parcelable.writeToParcel().
		*/
        @Override
        public Funcionarios createFromParcel(Parcel in) {
            //Retorna uma nova instância da classe Parcelable.
            return new Funcionarios(in);
        }

        //Crie uma nova matriz da classe Parcelable.
        @Override
        public Funcionarios[] newArray(int size) {
            //Retorna uma matriz da classe Parcelable, com cada entrada inicializada com null.
            return new Funcionarios[size];
        }
    };
}
