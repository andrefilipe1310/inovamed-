
import React from 'react';
import Navbar from "../../components/Navbar";
import PesquisaCard from './pesquisaCard';

const ListaPesquisas: React.FC = () => {
  const pesquisas = [
    { titulo: 'Mecanismos Moleculares e Genéticos no Alzheimer', id: '0020' },
    { titulo: 'Estilo de Vida e Prevenção do Alzheimer: Dieta e Exercício', id: '0021' },
    { titulo: 'Biomarcadores e Diagnóstico Precoce no Alzheimer', id: '0022' },
    { titulo: 'Novas terapias para prevenção do Alzheimer', id: '0023' },
    { titulo: 'Impacto da Genética no Alzheimer', id: '0024' },
    { titulo: 'Estudos sobre a Eficácia de Medicamentos', id: '0025' },
    { titulo: 'Avanços em Terapias Não-Farmacológicas', id: '0026' },
    { titulo: 'Relação entre Estresse e Alzheimer', id: '0027' },
  ];

  const estiloCabecalho: React.CSSProperties = {
    fontFamily: 'Montserrat-Bold',
    marginTop: '5vw',
    backgroundImage: 'linear-gradient(to top, #028275, #126b56)',
    backgroundClip: 'text',
    color: 'transparent',
    fontSize: '3vw',
    textAlign: 'center',
    marginBottom: '20px',
  };

  const estiloContainer: React.CSSProperties = {
    marginLeft: '25%', 
    padding: '20px', 
  };

  const estiloGrid: React.CSSProperties = {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'center',
    gap: '16px',
  };

  return (
    <>
      <Navbar />
      <div style={estiloContainer}>
        <h2 style={estiloCabecalho}>Pesquisas Inscritas</h2>
        <div style={estiloGrid}>
          {pesquisas.map((pesquisa) => (
            <PesquisaCard key={pesquisa.id} titulo={pesquisa.titulo} id={pesquisa.id} />
          ))}
        </div>
      </div>
    </>
  );
};

export default ListaPesquisas;
