import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.logging.LogManager;

// Ter em mente que o retrofit nao carrega de maneira natural nenhum conversor
// e aqui no caso estamos utilizando um conversor json
// por isso vamos colocar o Gson
// é importante tambem utilizar a mesma versao
// o receptor tambem eh um modulo a parte
public class Main {

    //private static LogManager.CloseOnReset GsonConverterFactory;
    public static void main(String[] args){

        System.out.println("Hello world");
        // Criamos um objeto do tipo retrofit e depois chamamos o servico


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // o nivel de debugger que queremos (nivel maximo)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.listRepos("juninhoojl");

        // Uma coisa que pode acontecer com uma invocacao remota eh falhar
        // ja que o servidor pode ter caiso, pode ter tempo excedido e etc

        // entao o que vmaos fazer é colocar dentro de um try
        // eh importatne nao ignorar as excessoes
        try {
            List <Repo> result = repos.execute().body();
            for (Repo r: result) {
                System.out.println(r);
            }

        } catch(Exception e){
            System.out.println("Exeption");
            System.out.println(e.toString());

        }

    }

}
