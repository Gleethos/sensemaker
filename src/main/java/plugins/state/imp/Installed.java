package plugins.state.imp;

import plugins.interfaces.IGame;

public class Installed extends Downloaded
{

    public Installed(IGame game) {
        super(game);
    }

    @Override
    public boolean start() {
        _setCallAction((game)->{
            game.start();
            return this;
        });
        return true;
    }

    @Override
    public boolean unborrow() {
        return false;
    }

    @Override
    public boolean borrow() {
        _setCallAction((game)->{
            game.borrow();
            return new Borrowed(game);
        });
        return true;
    }

    @Override
    public boolean uninstall() {
        _setCallAction((game)->{
            game.uninstall();
            return new Uninstalled(game);
        });
        return true;
    }

    @Override
    public boolean install() {
        return false;
    }

    @Override
    public boolean update() {
        _setCallAction((game)->{
            game.update();
            return this;
        });
        return true;
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
