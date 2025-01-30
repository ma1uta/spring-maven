package io.github.ma1uta.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
class DemoApplicationTests {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .build();
    }

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/api"))
            .andExpect(status().isOk())
            .andDo(document("index", responseFields(
                fieldWithPath("message").description("Some message")
            )));
    }

    @Test
    public void sum() throws Exception {
        this.mockMvc.perform(post("/api/sum").content("{\"x\":1,\"y\":2}").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("sum",
                requestFields(
                    fieldWithPath("x").description("x value"),
                    fieldWithPath("y").description("y value")
                ),
                responseFields(
                    fieldWithPath("result").description("Sum result")
                )
            ));
    }
}
