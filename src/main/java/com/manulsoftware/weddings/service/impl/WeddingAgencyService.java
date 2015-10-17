package com.manulsoftware.weddings.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import net.coobird.thumbnailator.Thumbnails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.IWeddingAgencyService;
import com.manulsoftware.weddings.service.PaginatedList;

@Service
public class WeddingAgencyService implements IWeddingAgencyService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeddingAgencyService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Integer saveAgency(WeddingAgency agency) {
		agency.setUpdated(new Date());
		agency.setSeolink(toPrettyURL(agency.getName()));
		
		if (agency.getId() == null) {
			agency.setCreated(agency.getUpdated());
			entityManager.persist(agency);
		} else {
			entityManager.merge(agency);
		}
		
		entityManager.flush();
		
		return agency.getId();
	}
	
	@Override
	@Transactional
	public Image createImage(final Image image) {
		image.setThumbnail(false);
		entityManager.persist(image);
		entityManager.flush();
		
		final Image thumbnail = generateThumbnail(image);
		if (thumbnail != null) {
			entityManager.persist(thumbnail);
			entityManager.flush();
			return thumbnail;
		}
		
		return image;
	}
	
	private Image generateThumbnail(final Image image) {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Thumbnails.of(new ByteArrayInputStream(image.getContent())).size(100, 100).toOutputStream(bos);
			final Image thumbnail = new Image();
			thumbnail.setAgencyId(image.getAgencyId());
			thumbnail.setContent(bos.toByteArray());
			thumbnail.setName("small_" + image.getName());
			thumbnail.setRelatedImageId(image.getId());
			thumbnail.setContentType(image.getContentType());
			thumbnail.setThumbnail(true);
			return thumbnail;
		} catch (IOException e) {
			logger.error("Error generating thumbnail", e);
			return null;
		}
		
	}
	
	@Override
	@Transactional
	public Image loadImage(Integer id) {
		return entityManager.find(Image.class, id);
	}
	
	@Override
	@Transactional
	public List<Image> loadThumbnailsForAgency(Integer agencyId) {
		return entityManager.createNamedQuery("Image.getThumbnailIdsForAgency", Image.class).setParameter("agencyId", agencyId).getResultList();
	}
	
	@Override
	@Transactional
	public void deleteImage(Integer id) {
		final Image image = loadImage(id);
		entityManager.remove(image);
		if (image.getRelatedImageId() != null) {
			final Image related = new Image();
			related.setId(image.getRelatedImageId());
			entityManager.remove(entityManager.merge(related));
		}
	}
	
	@Override
	@Transactional
	public List<WeddingAgency> getAllAgencies() {
		return entityManager.createNamedQuery("WeddingAgency.getAllAgencies", WeddingAgency.class).getResultList();
	}
	
	@Override
	@Transactional
	public PaginatedList<WeddingAgency> getAgenciesList(PaginatedList<WeddingAgency> pagination) {
		pagination.setTotalResults(countAgencies());
        int start = (pagination.getCurrentPage() - 1) * pagination.getPageSize();
        pagination.setList(findAgencies(start,
        		pagination.getPageSize(),
        		pagination.getSortField(),
        		pagination.getSortDirection()));
        pagination.setLastPage((pagination.getTotalResults() / pagination.getPageSize()) + 1);
        return pagination;
	}
	
	@Override
	public WeddingAgency loadAgency(final Integer id) {
		return entityManager.find(WeddingAgency.class, id);
	}
	
	@Override
	@Transactional
	public void deleteAgency(Integer id) {
		WeddingAgency agency = loadAgency(id);
		agency.setDeleted(true);
		saveAgency(agency);
	}
	
	@Override
	public List<String> getAllCountries() {
		return entityManager.createNamedQuery("WeddingAgency.getAllCountries", String.class).getResultList();
	}
	
	private Integer countAgencies() {
        Query query = entityManager.createQuery("SELECT COUNT(w.id) FROM WeddingAgency w");
        return ((Long) query.getSingleResult()).intValue();
    }
	
	private List<WeddingAgency> findAgencies(int startPosition, int maxResults, String sortFields, String sortDirections) {
        TypedQuery<WeddingAgency> query = entityManager.createQuery("SELECT w FROM WeddingAgency w WHERE w.deleted = false ORDER BY " + sortFields + " " + sortDirections, WeddingAgency.class);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
	
	private static String toPrettyURL(String string) {
	    return Normalizer.normalize(string.toLowerCase(), Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	        .replaceAll("[^\\p{Alnum}]+", "-");
	}
}
