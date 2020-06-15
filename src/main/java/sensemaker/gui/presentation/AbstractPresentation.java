package sensemaker.gui.presentation;

import sensemaker.datalayer.API.DAL;
import sensemaker.datalayer.assembly.DALFactory;

/**
 *
 */
public abstract class AbstractPresentation<ModelType>
{

    private static DAL _dal;
    static
    {
        _dal = new DALFactory().setDoMocking(false).produce();
    }

    protected DAL _getDAL(){
        return _dal;
    }

    public abstract void refresh(ModelType model);

    public abstract void applyChanges(ModelType model);

}
