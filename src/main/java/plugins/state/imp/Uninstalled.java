package plugins.state.imp;

import plugins.interfaces.IGame;

public class Uninstalled extends Downloaded {

    public Uninstalled(IGame game) {
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
        _setCallAction((game)->{
            game.borrow();
            return new Borrowed(game);
        });
        return false;
    }

    @Override
    public boolean uninstall() {
        _setCallAction((game)->{
            game.uninstall();
            return new Uninstalled(game);
        });
        return false;
    }

    @Override
    public boolean install() {
        _setCallAction((game)->{
            game.install();
            return new Installed(game);
        });
        return true;
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
        _setCallAction((game)->{
            game.sell();
            return new Known(game);
        });
        return false;
    }
}
