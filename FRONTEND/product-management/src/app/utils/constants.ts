const FUNCTION_TYPE = {
    READ: 'READ',
    UPDATE: 'UPDATE',
    CREATE: 'CREATE'
}

const CATEGORIES = {
    UNFORMATTED: {
        PERISHABLE: 'perecivel',
        NOT_PERISHABLE: 'nao_perecivel'
    },
    FORMATTED: {
        PERISHABLE: 'Perecível',
        NOT_PERISHABLE: 'Não Perecível'
    }
}

const UPDATE_TITLE = 'Alterar Produto'
const SHOW_TITLE = 'Exibir Produto'

export {
    FUNCTION_TYPE,
    CATEGORIES,
    UPDATE_TITLE,
    SHOW_TITLE
}