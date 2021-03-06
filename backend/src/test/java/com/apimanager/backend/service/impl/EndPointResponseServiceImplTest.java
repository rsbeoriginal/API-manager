package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.repository.EndPointResponseFragmentRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class EndPointResponseServiceImplTest {

  @InjectMocks
  private EndPointResponseServiceImpl endPointResponseService;

  @Mock
  private EndPointResponseFragmentRepository endPointResponseFragmentRepository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void fetchEndpointResponse() {
  }

  @Test
  public void insertEndpointResponse() throws JSONException {

    Mockito.doNothing().when(endPointResponseFragmentRepository).markForDelete(Mockito.anyString());
    Mockito.when(endPointResponseFragmentRepository.findOneByEndPointAndAttributePath(Mockito.any(Endpoint.class),Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(endPointResponseFragmentRepository.save(Mockito.any(EndPointResponseFragment.class))).thenReturn(null);

    JSONObject jsonObject = new JSONObject("{\n" +
            "  \"glossary\": {\n" +
            "    \"title\": \"example glossary\",\n" +
            "    \"GlossDiv\": {\n" +
            "      \"title\": \"S\",\n" +
            "      \"GlossList\": {\n" +
            "        \"GlossEntry\": {\n" +
            "          \"ID\": \"SGML\",\n" +
            "          \"SortAs\": \"SGML\",\n" +
            "          \"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
            "          \"Acronym\": \"SGML\",\n" +
            "          \"Abbrev\": \"ISO 8879:1986\",\n" +
            "          \"GlossDef\": {\n" +
            "            \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
            "            \"GlossSeeAlso\": [\n" +
            "              {\"name\":\"GML\"},\n" +
            "              {\"name\":\"ACV\"}\n" +
            "            ]\n" +
            "          },\n" +
            "          \"GlossSee\": true\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}");


    endPointResponseService.insertEndpointResponse("E1",jsonObject);

  }
}