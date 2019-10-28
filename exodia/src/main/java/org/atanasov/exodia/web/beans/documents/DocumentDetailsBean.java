package org.atanasov.exodia.web.beans.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

@Model
@Getter
@Setter
@NoArgsConstructor
public class DocumentDetailsBean {

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;
}
