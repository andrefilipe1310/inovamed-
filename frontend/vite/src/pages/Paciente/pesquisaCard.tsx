import React from 'react';
import { Link } from 'react-router-dom';
 
 
interface PesquisaCardProps {
  titulo: string;
  code: number;
}
 
const PesquisaCard: React.FC<PesquisaCardProps> = ({ titulo, code }) => {
  return (
   
    <div style={estiloCard}>
       <Link to={`/representante/infoPesquisas?id=${code}`} className="link-to-pesq">
      <h3 style={{ color: '#028275' }}>{titulo}</h3>
      <p style={{ color: 'black' }}>#{code}</p>
      </Link>
    </div>
  );
};
 
const estiloCard: React.CSSProperties = {
  border: '1px solid #ccc',
  borderRadius: '8px',
  padding: '16px',
  margin: '8px',
  width: '250px',
  textAlign: 'center',
  backgroundColor: '#f9f9f9',
};
 
export default PesquisaCard;
 
