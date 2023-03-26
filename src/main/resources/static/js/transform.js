const RESULT_LIST = 'results';

export function normalizeAuthors(data) {
    const NESTED_PROPERTIES = [
        [RESULT_LIST, 'COUNTRY_ID'],
        ['authors', 'AUTHOR_ID', 'COUNTRY_NAME', 'COUNTRY_ID'],
        ['posts', 'POST_ID', 'AUTHOR_NAME', 'AUTHOR_ID'],
        ['likes', 'FAN_ID', 'POST_NAME', 'POST_ID']
    ];

    return normalize(data, NESTED_PROPERTIES);
}
function normalize(data, levels) {
    return arrayToObject(objectsFromRows(data), levels)[RESULT_LIST];
}

function objectsFromRows(rows) {
    const headers = rows[0];
    return rows.slice(1)
            .map(row => Object.fromEntries(headers.map((h, i) => [h, row[i]])
                        .filter(([k, v]) => v !== null)));
}

function rowsWithSameId(list, id) {

    const map = {};

    list.forEach(o => {
        let k = "k" + o[id];  // convert to string to prevent sorting by array index
        map[k] ? map[k].push(o) : (map[k] = [o]);
    });

    return Object.values(map);
}

function pick(fromObj, properties) {
    return Object.fromEntries(properties.filter(k => k in fromObj).map(k => [k, fromObj[k]]));
}

function arrayToObject(ar, levels) {
    const [children, childId, ...parentProperties] = levels[0];

    const o = pick(ar[0], parentProperties);

    const list = ar.filter(o => o[childId]); // remove the righthand part of empty left joins

    if (list.length) {
        if (levels.length > 1) {
            o[children] = rowsWithSameId(list, childId)
                    .map(ar2 => arrayToObject(ar2, levels.slice(1)));
        } else {
            o[children] = list; // do not group the last level
        }
    }

    ar.forEach(e => parentProperties.forEach(k => delete e[k]));

    return o;
}