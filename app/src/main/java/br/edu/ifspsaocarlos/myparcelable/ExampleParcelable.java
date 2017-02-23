package br.edu.ifspsaocarlos.myparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chefer on 14/02/17.
 */
public class ExampleParcelable implements Parcelable {
    String name;
    int idade;
    public ExampleParcelable() {}
    public ExampleParcelable(String name, int idade) {
        this.name = name;
        this.idade = idade;
    }
    public String getName() {
        return name;
    }
    public int getIdade() {
        return idade;
    }

  	/*
	Parcelable fornece um protocolo extremamente eficiente para escrever e ler objetos
	a partir de Parcels.
	*/

    /*
	construtor >> Observe que Parcelable chama o construtor da classe que
	recebe um Parcel permitindo ler dados e passar os atributos.
	*/
    protected ExampleParcelable(Parcel in) {
        name = in.readString();
        idade = in.readInt();
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
        dest.writeString(name);
        dest.writeInt(idade);
    }

	/*
	Classe aninhada >> Interface que deve ser implementada e abastecida com um
	campo público CREATOR que gera instâncias da classe Parcelable a partir de Parcel.
	*/
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ExampleParcelable> CREATOR = new Parcelable.Creator<ExampleParcelable>() {
        /*
		Cria uma nova instância da classe Parcelable, instanciando-a de Parcel,
		cujos dados foram previamente escritos por Parcelable.writeToParcel().
		*/
        @Override
        public ExampleParcelable createFromParcel(Parcel in) {
            //Retorna uma nova instância da classe Parcelable.
            return new ExampleParcelable(in);
        }

        //Crie uma nova matriz da classe Parcelable.
        @Override
        public ExampleParcelable[] newArray(int size) {
            //Retorna uma matriz da classe Parcelable, com cada entrada inicializada com null.
            return new ExampleParcelable[size];
        }
    };
}