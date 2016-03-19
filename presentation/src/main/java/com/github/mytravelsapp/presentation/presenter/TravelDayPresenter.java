package com.github.mytravelsapp.presentation.presenter;

import com.github.mytravelsapp.business.Utils;
import com.github.mytravelsapp.business.dto.TravelPlacesDto;
import com.github.mytravelsapp.business.interactor.Callback;
import com.github.mytravelsapp.business.interactor.FindTravelPlacesByIdsInteractor;
import com.github.mytravelsapp.business.interactor.SaveTravelInteractor;
import com.github.mytravelsapp.presentation.converter.TravelModelConverter;
import com.github.mytravelsapp.presentation.converter.TravelPlacesModelConverter;
import com.github.mytravelsapp.presentation.model.TravelDayPlanningModel;
import com.github.mytravelsapp.presentation.model.TravelModel;
import com.github.mytravelsapp.presentation.model.TravelPlacesModel;
import com.github.mytravelsapp.presentation.navigation.Navigator;
import com.github.mytravelsapp.presentation.view.TravelDayView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * @author fjtorres
 */
public class TravelDayPresenter extends AbstractPresenter<TravelDayView> {

    private final TravelPlacesModelConverter converter;

    private final TravelModelConverter travelModelConverter;

    private final FindTravelPlacesByIdsInteractor findTravelPlacesByIdsInteractor;

    private final SaveTravelInteractor saveTravelInteractor;

    @Inject
    public TravelDayPresenter(final Navigator pNavigator, final FindTravelPlacesByIdsInteractor pFindTravelPlacesByIdsInteractor, final TravelModelConverter pTravelModelConverter, final TravelPlacesModelConverter pConverter, final SaveTravelInteractor pSaveTravelInteractor) {
        super(pNavigator);
        this.findTravelPlacesByIdsInteractor = pFindTravelPlacesByIdsInteractor;
        this.travelModelConverter = pTravelModelConverter;
        this.converter = pConverter;
        this.saveTravelInteractor = pSaveTravelInteractor;
    }

    public void load(final Date selectedDay) {
        final TravelModel model = getView().getCurrentModel();

        final List<TravelDayPlanningModel> daysPlanning = model.getDaysPlanning();
        if (!Utils.isEmpty(daysPlanning)) {

            getView().showLoading();
            final List<Long> travelPlacesIds = new ArrayList<>(daysPlanning.size());

            for (final TravelDayPlanningModel dayPlanning : daysPlanning) {
                if (dayPlanning.getDay().equals(selectedDay)) {
                    travelPlacesIds.add(dayPlanning.getTravelPlaceId());
                }
            }
            findTravelPlacesByIdsInteractor.setTravelPlaceIds(travelPlacesIds);

            findTravelPlacesByIdsInteractor.execute(new Callback<List<TravelPlacesDto>>() {
                @Override
                public void onSuccess(List<TravelPlacesDto> result) {
                    getView().hideLoading();
                    getView().renderList(converter.convert(result));
                }

                @Override
                public void onError(final Throwable cause) {
                    getView().hideLoading();
                    // FIXME SHOW ERROR!!!!
                }
            });
        }

    }

    public void remove(final Date selectedDay, final TravelPlacesModel placeToRemove) {
        final TravelModel model = getView().getCurrentModel();

        for (final TravelDayPlanningModel dayPlanning : model.getDaysPlanning()) {
            if (dayPlanning.getDay().equals(selectedDay) && dayPlanning.getTravelPlaceId().equals(placeToRemove.getId())) {
                model.getDaysPlanning().remove(dayPlanning);
            }
        }

        saveTravelInteractor.setData(travelModelConverter.convertToDto(model));
        saveTravelInteractor.execute(new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {

            }

            @Override
            public void onError(Throwable cause) {
                // FIXME Show error
            }
        });
    }
}
