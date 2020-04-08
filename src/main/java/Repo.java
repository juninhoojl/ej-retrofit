public class Repo {

    // Eh importante que os nomes sejam os mesmos do json
    // assim o mapping vai ser automatico
    int id;
    String name;
    String fullName;

    // Aqui estamos rescrevendo a funcao que extendeu da classe pai Objeto
    // a funcao toString
    @Override
    public String toString(){

        return id+" "+name + " > " + fullName;
    }

}
