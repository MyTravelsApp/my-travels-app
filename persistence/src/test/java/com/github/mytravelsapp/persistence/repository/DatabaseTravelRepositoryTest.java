package com.github.mytravelsapp.persistence.repository;

import com.github.mytravelsapp.business.dto.TravelDto;
import com.github.mytravelsapp.business.exception.PersistenceException;
import com.github.mytravelsapp.business.repository.TravelPlacesRepository;
import com.github.mytravelsapp.persistence.converter.TravelConverter;
import com.github.mytravelsapp.persistence.entity.Travel;
import com.github.mytravelsapp.persistence.helper.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Unit test for {@link DatabaseTravelRepository}.
 *
 * @author fjtorres
 */
public class DatabaseTravelRepositoryTest {

    @Mock
    private TravelPlacesRepository mockTravelPlacesRepository;

    @Mock
    private TravelConverter mockConverter;

    @Mock
    private DatabaseHelper mockDbHelper;

    @Mock
    private Dao mockDao;

    private DatabaseTravelRepository dbTravelRepository;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        this.dbTravelRepository = new DatabaseTravelRepository(mockTravelPlacesRepository, mockConverter, mockDbHelper);

        when(mockDbHelper.getDao(Travel.class)).thenReturn(mockDao);
    }

    @Test
    public void saveCreateTest() throws PersistenceException, SQLException {
        final Travel entity = new Travel(-1);

        when(mockConverter.convert(any(TravelDto.class))).thenReturn(entity);

        dbTravelRepository.save(new TravelDto());

        verify(mockConverter).convert(any(TravelDto.class));
        verify(mockDao).create(entity);
    }

    @Test
    public void saveUpdateTest() throws PersistenceException, SQLException {
        final Travel entity = new Travel(1);

        when(mockConverter.convert(any(TravelDto.class))).thenReturn(entity);

        dbTravelRepository.save(new TravelDto());

        verify(mockConverter).convert(any(TravelDto.class));
        verify(mockDao).update(entity);
    }

    @Test
    public void saveNullTest() throws PersistenceException {
        dbTravelRepository.save(null);
    }

    @Test(expected = PersistenceException.class)
    public void savePersistenceExceptionTest() throws PersistenceException, SQLException {
        final Travel entity = new Travel(-1);

        when(mockConverter.convert(any(TravelDto.class))).thenReturn(entity);
        when(mockDao.create(entity)).thenThrow(SQLException.class);

        dbTravelRepository.save(new TravelDto());
    }
}