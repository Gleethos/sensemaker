package plugins.state.imp;

import plugins.interfaces.IGame;
import plugins.state.AbstractState;

public class Known extends AbstractState {

    public Known(IGame game) {
        super(game);
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean unborrow() {
        return false;
    }

    @Override
    public boolean borrow() {
        return false;
    }

    @Override
    public boolean uninstall() {
        return false;
    }

    @Override
    public boolean install() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean download() {
        return false;
    }

    @Override
    public boolean buy() {
        return false;
    }

    @Override
    public boolean sell() {
        return false;
    }
}
