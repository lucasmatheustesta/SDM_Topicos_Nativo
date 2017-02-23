package br.edu.ifspsaocarlos.myparcelable.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import br.edu.ifspsaocarlos.myparcelable.ExampleParcelable;

/**
 * Created by lucas on 15/02/17.
 */
/*
    Link para a biblioteca: https://github.com/johncarl81/parceler
    Após a instalação do framework vide build.gradle (app)
    Para transformar um POJO em um Parcelable, somente é preciso
    inserir o decorador @Parcel na classe.

    A grande vantagem é que não é preciso criar tanto "boilerplate"
    e o resultado é o mesmo. Não é preciso implementar interface alguma,
    nem sobreescrever o método writeToParcel ou createFromParcel e nem mesmo
    declarar uma classe aninhada.

    Uma desvantagem é que é preciso inserir mais uma dependência no projeto.
    Somente alguns tipos de campos podem ser utilizados com esse decorador
    a lista se encontra nesse link: https://github.com/johncarl81/parceler#parcel-attribute-types
    Atributos private são penalizados em performance, mas ainda sim é muito mais rápido que Serialization
*/

public class Empresa implements Parcelable {
    private String nomeEmpresa;
    private ArrayList<Funcionarios> funcionarios = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(String nomeEmpresa, ArrayList<Funcionarios> funcionarios) {
        this.nomeEmpresa = nomeEmpresa;
        this.funcionarios = funcionarios;
    }


    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public ArrayList<Funcionarios> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionarios> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void addFuncionario(Funcionarios f) {
        funcionarios.add(f);
    }


    @Override
    public String toString() {
        return nomeEmpresa;
    }

    /*
	Parcelable fornece um protocolo extremamente eficiente para escrever e ler objetos
	a partir de Parcels.
	*/

    /*
	construtor >> Observe que Parcelable chama o construtor da classe que
	recebe um Parcel permitindo ler dados e passar os atributos.
	*/
    protected Empresa(Parcel in) {
        nomeEmpresa = in.readString();
        funcionarios = in.readArrayList(Funcionarios.class.getClassLoader());
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
        dest.writeString(nomeEmpresa);
        dest.writeList(funcionarios);
    }

    /*
    Classe aninhada >> Interface que deve ser implementada e abastecida com um
    campo público CREATOR que gera instâncias da classe Parcelable a partir de Parcel.
    */
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Empresa> CREATOR = new Parcelable.Creator<Empresa>() {
        /*
		Cria uma nova instância da classe Parcelable, instanciando-a de Parcel,
		cujos dados foram previamente escritos por Parcelable.writeToParcel().
		*/
        @Override
        public Empresa createFromParcel(Parcel in) {
            //Retorna uma nova instância da classe Parcelable.
            return new Empresa(in);
        }

        //Crie uma nova matriz da classe Parcelable.
        @Override
        public Empresa[] newArray(int size) {
            //Retorna uma matriz da classe Parcelable, com cada entrada inicializada com null.
            return new Empresa[size];
        }
    };
}
