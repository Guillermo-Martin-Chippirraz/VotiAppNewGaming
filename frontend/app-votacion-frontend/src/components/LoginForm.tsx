import { useState } from "react";
import { useAuth } from "../hooks/useAuth"

export const LoginForm = () => {
    const { login, state } = useAuth();

    const [username, setUseername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e: React.SyntheticEvent) => {
        e.preventDefault();
        await login({ username, password });
    };

    return (
        <form
            onSubmit={handleSubmit}
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "1rem",
                width: "100%",
            }}
        >
            <input
                className="input"
                type="text"
                placeholder="Nombre de usuario"
                value={username}
                onChange={(e) => setUseername(e.target.value)}
                required
                style={{ width: "100%" }}
            />

            <input
                className="input"
                type="password"
                placeholder="Contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                style={{ width: "100%" }}
            />

            <button className="btn-primary" type="submit" disabled={state.loading}>
                {state.loading ? "Entrando..." : "Entrar"}
            </button>
        </form>
    );
};