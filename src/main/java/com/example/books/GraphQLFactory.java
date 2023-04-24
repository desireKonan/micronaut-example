package com.example.books;

import com.example.GraphQLService;
import com.example.books.resolvers.HelloDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.inject.qualifiers.Qualifiers;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Factory
public class GraphQLFactory {

    public static final Logger logger = LoggerFactory.getLogger(GraphQLFactory.class);

    @Inject
    protected BeanContext beanContext;

    @Bean
    @Singleton
    public GraphQL graphQL(ResourceResolver resourceResolver, HelloDataFetcher helloDataFetcher) { //

        GraphQLSchemaGenerator schemaGenerator = new GraphQLSchemaGenerator();

        Collection<Object> graphQLServices = beanContext.getBeansOfType(Object.class, Qualifiers.byStereotype(GraphQLService.class)); // 2

        if (graphQLServices.isEmpty()) { // 3
            logger.debug("No GraphQL services found, returning empty schema");
            return new GraphQL.Builder(GraphQLSchema.newSchema().build())
                    .build();
        } else { // 4
            for (Object graphQLService: graphQLServices) {
                Class graphQLServiceClass = graphQLService.getClass();
                if (graphQLServiceClass.getSimpleName().contains("$Intercepted"))
                    graphQLServiceClass = graphQLServiceClass.getSuperclass(); // 5

                logger.debug("Registering GraphQL service: {}", graphQLServiceClass.getSimpleName());
                schemaGenerator.withOperationsFromSingleton(graphQLService, graphQLServiceClass); // 6
            }
        }

        return new GraphQL.Builder(schemaGenerator.generate()).build();
    }
}
