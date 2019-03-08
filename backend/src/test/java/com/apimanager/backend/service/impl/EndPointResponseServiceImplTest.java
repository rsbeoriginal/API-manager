package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Endpoint;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class EndPointResponseServiceImplTest {

  @InjectMocks
  private EndPointResponseServiceImpl endPointResponseService;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void fetchEndpointResponse() {
  }

  @Test
  public void insertEndpointResponse() throws JSONException {

    JSONObject jsonObject = new JSONObject("{\n" +
            "    \"glossary\": {\n" +
            "        \"title\": \"example glossary\",\n" +
            "\t\t\"GlossDiv\": {\n" +
            "            \"title\": \"S\",\n" +
            "\t\t\t\"GlossList\": {\n" +
            "                \"GlossEntry\": {\n" +
            "                    \"ID\": \"SGML\",\n" +
            "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
            "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
            "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
            "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
            "\t\t\t\t\t\"GlossDef\": {\n" +
            "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
            "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
            "                    },\n" +
            "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}");

    Endpoint endpoint = new Endpoint();

    endPointResponseService.insertEndpointResponse(endpoint,jsonObject);

  }
}