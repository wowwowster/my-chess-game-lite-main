module.exports = {
  parser: '@typescript-eslint/parser',
  extends: ['eslint:recommended', '@vue/eslint-config-typescript', 'prettier'],
  root: true,
  plugins: ['cypress', '@typescript-eslint'],
  env: {
    node: true,
    mocha: true,
    'cypress/globals': true,
  },
};
