package ui;

import state.data.EncapsulatedGolState;

public interface Observer {

    void recieveGolStateEncapsulated(EncapsulatedGolState state);

}
