package plugins.state;

import plugins.interfaces.IGame;

public class GameWrapper implements IGame
{
    private AbstractState _state;

    public GameWrapper(AbstractState initialState){
        _state = initialState;
    }

    @Override
    public boolean start() {
        _state = _state.update(()->_state.start());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean unborrow() {
        _state = _state.update(()->_state.unborrow());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean borrow() {
        _state = _state.update(()->_state.borrow());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean uninstall() {
        _state = _state.update(()->_state.uninstall());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean install() {
        _state = _state.update(()->_state.install());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean update() {
        _state = _state.update(()->_state.update());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean download() {
        _state = _state.update(()->_state.download());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean buy() {
        _state = _state.update(()->_state.buy());
        return _state.lastCallWasSuccessful();
    }

    @Override
    public boolean sell() {
        _state = _state.update(()->_state.sell());
        return _state.lastCallWasSuccessful();
    }
}
