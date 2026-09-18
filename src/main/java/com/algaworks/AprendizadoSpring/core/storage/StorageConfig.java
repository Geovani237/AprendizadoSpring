package com.algaworks.AprendizadoSpring.core.storage;

import com.algaworks.AprendizadoSpring.domain.service.FotoStorageService;
import com.algaworks.AprendizadoSpring.infrastructure.service.storage.LocalFotoStorageService;
import com.algaworks.AprendizadoSpring.infrastructure.service.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.client.builder.AwsClientBuilder;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    //Acessando o LocalStack
    @Bean
    @ConditionalOnProperty(
            name = "algafood.storage.tipo",
            havingValue = "s3"
    )
    public AmazonS3 amazonS3() {
        //BasicAWSCredentials guarda access key e secret key
        var credenciais = new BasicAWSCredentials(
                storageProperties.getS3().getIdChaveAcesso(),
                storageProperties.getS3().getChaveAcessoSecreta());

        //AwsClientBuilder permite configurar um endpoint diferente do padrão da AWS.
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                "http://localhost:4566",
                storageProperties.getS3().getRegiao().getName());

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credenciais))//AWSStaticCredentialsProvider entrega essas credenciais ao cliente
                .withPathStyleAccessEnabled(true)//Sem esse comando o SDK pode tentar acessar o bucker usando o formato virtual-hosted style
                .build();
    }

    //Acessando o AWS real
//    @Bean
//    @ConditionalOnProperty(name = "algafood.storage.tipo", havingValue = "s3")
//    public AmazonS3 amazonS3() {
//        var credencials = new BasicAWSCredentials(
//                storageProperties.getS3().getIdChaveAcesso(),
//                storageProperties.getS3().getChaveAcessoSecreta());
//
//        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credencials))
//                .withRegion(storageProperties.getS3().getRegiao())
//                .build();
//    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }
}
