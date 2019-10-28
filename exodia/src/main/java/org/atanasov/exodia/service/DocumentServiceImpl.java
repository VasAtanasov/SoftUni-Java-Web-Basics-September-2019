package org.atanasov.exodia.service;

import org.atanasov.exodia.domain.entities.Document;
import org.atanasov.exodia.domain.models.service.DocumentScheduleServiceModel;
import org.atanasov.exodia.domain.models.view.DocumentTitleViewModel;
import org.atanasov.exodia.domain.models.view.DocumentViewModel;
import org.atanasov.exodia.repository.DocumentRepository;
import org.atanasov.exodia.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private static final Function<DocumentTitleViewModel, DocumentTitleViewModel> MAP_TITLE =
            document -> {
                String title = document.getTitle();

                if (title.length() > 12) {
                    title = title.substring(0, 12);
                }

                document.setTitle(title + "...");

                return document;
            };

    private final DocumentRepository documentRepository;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapperWrapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocumentTitleViewModel> getTitles() {
        return documentRepository.findAll()
                .stream()
                .map(document -> modelMapper.map(document, DocumentTitleViewModel.class))
                .map(MAP_TITLE)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentViewModel scheduleDocument(DocumentScheduleServiceModel model) {
        Document document = documentRepository.save(modelMapper.map(model, Document.class));
        return modelMapper.map(document, DocumentViewModel.class);
    }
}
