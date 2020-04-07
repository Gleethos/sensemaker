package plugins.state;

import plugins.interfaces.IGame;

public abstract class AbstractState implements IGame {

    public interface CallAction { AbstractState updateOn(IGame game); }
    public interface Call{ boolean execute();}

    private CallAction _callAction;

    private IGame _game;

    private boolean _lastCallSuccess;

    public AbstractState(IGame game){
        _game = game;
    }

    protected void _setCallAction(CallAction callAction){
        _callAction = callAction;
    }

    public AbstractState update(Call c){
        _lastCallSuccess = c.execute();
        if(_callAction ==null) return this;
        AbstractState newState = _callAction.updateOn(_game);
        if(newState!=null) return newState;
        _callAction =null;
        return this;
    }

    public boolean lastCallWasSuccessful(){
        return _lastCallSuccess;
    }
}
