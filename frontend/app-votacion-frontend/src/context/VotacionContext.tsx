import { createContext, useReducer } from "react";
import { votacionInitialState, votacionReducer, type VotacionState } from "../reducers/votacion.reducer";
import { votacionService } from "../services/votacion";

interface VotacionContextProps {
    state: VotacionState;
    cargarVotaciones: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const VotacionContext = createContext<VotacionContextProps>(null!);

export const VotacionProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(votacionReducer, votacionInitialState);

    const cargarVotaciones = async () => {
        dispatch({ type: "SET_LOADING", payload: true });
        const votaciones = await votacionService.obtenerTodas();
        dispatch({ type: "SET_VOTACIONES", payload: votaciones });
    };

    return (
        <VotacionContext.Provider value={{ state, cargarVotaciones }}>
            {children}
        </VotacionContext.Provider>
    );
};