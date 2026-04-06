import { createContext, useReducer } from "react";
import { eventoInitialState, eventoReducer, type EventoState } from "../reducers/evento.reducer";
import { eventoService } from "../services/evento";

export interface EventoContextProps {
    state: EventoState;
    cargarEventos: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const EventoContext = createContext<EventoContextProps>(null!);

export const EventoProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(eventoReducer, eventoInitialState);

    const cargarEventos = async () => {
        dispatch({ type: "SET_LOADING", payload: true });
        const eventos = await eventoService.obtenerTodos();
        dispatch({ type: "SET_EVENTOS", payload: eventos });
    };

    return (
        <EventoContext.Provider value={{ state, cargarEventos }}>
            {children}
        </EventoContext.Provider>
    )
}