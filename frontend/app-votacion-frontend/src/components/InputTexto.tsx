import './InputTexto.css';

interface InputTextoProps {
  placeholder: string;
  valor: string;
  onChange: (valor: string) => void;
  tipo?: 'text' | 'password';
}

export const InputTexto = ({ placeholder, valor, onChange, tipo = 'text' }: InputTextoProps) => {
  return (
    <div className="input-texto-contenedor">
      <input
        type={tipo}
        className="input-texto"
        placeholder={placeholder}
        value={valor}
        onChange={(e) => onChange(e.target.value)}
      />
    </div>
  );
};