import React from 'react';
 
 
interface PesquisaCardProps {
  titulo: string;
  id: string;
}
 
const PesquisaCard: React.FC<PesquisaCardProps> = ({ titulo, id }) => {
  return (
   
    <div style={estiloCard}>
      <h3 style={{ color: '#028275' }}>{titulo}</h3>
      <p style={{ color: 'black' }}>#{id}</p>
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
 
