export function compare(a, b) {
    a = toJSON(a);
    b = toJSON(b);
    console.assert(a === b, a + " != " + b);
}
function toJSON(o) {
    return JSON.stringify(sortKeys(o)).replaceAll(/"FAN_/g, '"AUTHOR_');
}
function sortKeys(o) {
    if (Array.isArray(o))
        return o.map(e => sortKeys(e));

    return Object.fromEntries(Object.entries(o).sort((a, b) => a[0].localeCompare(b[0]))
            .map(([k, v]) => {
                if (Array.isArray(v))
                    return [k, sortKeys(v)];
                return [k, v];
            }));
}

const a = {a: 1, c: [{g: 1, e: 1}, {e: 1, i: 1, h: 1}], b: 3, '1': 1};
console.log(sortKeys(a));