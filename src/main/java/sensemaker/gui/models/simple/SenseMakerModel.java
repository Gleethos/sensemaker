package sensemaker.gui.models.simple;

import sensemaker.gui.models.AbstractModel;

import java.util.Map;

public class SenseMakerModel extends AbstractModel<SenseMakerModel>
{
    public SenseMakerModel()
    {
        super(null, null, null);
    }

    /**
     * This method should be handled with caution as it
     * generates random values for model fields that have not been set!
     * It uses a seed String as a source of pseudo randomness...
     *
     * @param seed A String from which its hash code is used as source of pseudo randomness
     * @return The instance itself. := Factory Pattern!
     */
    @Override
    public SenseMakerModel completeRandomly(String seed) {
        return null;
    }

    @Override
    public String getAsTableName() {
        return null;
    }

    @Override
    public <T> Map<String, T> generatePreparedSQLKeyValues(Class<T> type) {
        return null;
    }
}
