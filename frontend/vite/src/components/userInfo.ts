
export interface Pesquisa {
    id: number;
    titulo: string;
    desc: string;
    dataini: string;
    datafim: string;
  }

  const pesquisas: Pesquisa[] =[
    {
        id: 1,
        titulo: "Genética e Doença de Alzheimer",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Estudo sobre os genes associados ao aumento do risco de desenvolver a Doença de Alzheimer, focando em variantes genéticas e sua relação com a patologia."
    },
    {
        id: 2,
        titulo: "Impacto da Dieta Mediterrânea na Progressão do Alzheimer",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Pesquisa que analisa como a adesão à dieta mediterrânea pode influenciar a saúde cognitiva e retardar a progressão da Doença de Alzheimer em idosos."
    },
    {
        id: 3,
        titulo: "Terapias Cognitivas e Alzheimer",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Investigação sobre a eficácia de intervenções de terapia cognitiva e comportamental na melhoria da qualidade de vida de pacientes com Alzheimer."
    },
    {
        id: 4,
        titulo: "Biomarcadores de Alzheimer em Fluídos Corporais",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Estudo que busca identificar biomarcadores específicos em fluidos corporais, como sangue e líquido cefalorraquidiano, para diagnóstico precoce da Doença de Alzheimer."
    },
    {
        id: 5,
        titulo: "A Relação entre Estresse e Alzheimer",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Análise da correlação entre níveis elevados de estresse crônico e o aumento do risco de desenvolver a Doença de Alzheimer em populações idosas."
    },
    {
        id: 6,
        titulo: "Atividade Física e Prevenção do Alzheimer",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Estudo sobre como a prática regular de exercícios físicos pode diminuir o risco de desenvolver Alzheimer e melhorar a função cognitiva em pacientes diagnosticados."
    },
    {
        id: 7,
        titulo: "A Influência do Sono na Saúde Cerebral",
        dataini: "Data de inicio: 27/09/2024",
        datafim: "Data Conclusão: 30/11/2024",
        desc: "Pesquisa que investiga como padrões de sono afetam a saúde cerebral e a progressão da Doença de Alzheimer, com foco na relação entre distúrbios do sono e demência."
    }
]

export default pesquisas;